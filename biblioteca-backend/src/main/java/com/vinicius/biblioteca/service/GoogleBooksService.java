package com.vinicius.biblioteca.service;

import com.vinicius.biblioteca.dto.GoogleBooksDTO;
import com.vinicius.biblioteca.enums.Categoria;
import com.vinicius.biblioteca.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class GoogleBooksService {

    @Autowired
    private LivroService livroService;

    public GoogleBooksDTO buscarLivroNaGoogle(String titulo) {
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + titulo;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, GoogleBooksDTO.class);
    }

    public Livro salvarLivro(String titulo) {
        GoogleBooksDTO resposta = buscarLivroNaGoogle(titulo);

        if (resposta == null || resposta.getItems() == null || resposta.getItems().isEmpty()) {
            throw new RuntimeException("Nenhum livro encontrado no Google Books com esse título.");
        }

        GoogleBooksDTO.VolumeInfo infoGoogle = resposta.getItems().get(0).getVolumeInfo();

        Livro novoLivro = new Livro();
        novoLivro.setTitulo(infoGoogle.getTitle());

        if (infoGoogle.getAuthors() != null && !infoGoogle.getAuthors().isEmpty()) {
            novoLivro.setAutor(infoGoogle.getAuthors().get(0));
        } else {
            novoLivro.setAutor("Desconhecido");
        }

        String isbn = "N/A";
        if (infoGoogle.getIndustryIdentifiers() != null) {
            for (GoogleBooksDTO.IndustryIdentifier id : infoGoogle.getIndustryIdentifiers()) {
                if ("ISBN_13".equals(id.getType()) || "ISBN_10".equals(id.getType())) {
                    isbn = id.getIdentifier();
                    break;
                }
            }
        }
        novoLivro.setIsbn(isbn);
        novoLivro.setDataPublicacao(LocalDate.now());

        novoLivro.setCategoria(traduzirCategoriaGoogle(infoGoogle.getCategories()));

        return livroService.salvar(novoLivro);
    }

    private Categoria traduzirCategoriaGoogle(List<String> categoriasGoogle) {
        if (categoriasGoogle == null || categoriasGoogle.isEmpty()) {
            return Categoria.OUTROS;
        }

        String catGoogle = categoriasGoogle.get(0).toLowerCase();

        if (catGoogle.contains("fantasy") || catGoogle.contains("magic")) return Categoria.FANTASIA;
        if (catGoogle.contains("romance") || catGoogle.contains("love")) return Categoria.ROMANCE;
        if (catGoogle.contains("horror") || catGoogle.contains("thriller") || catGoogle.contains("scary")) return Categoria.TERROR;
        if (catGoogle.contains("computer") || catGoogle.contains("technology") || catGoogle.contains("programming")) return Categoria.TECNOLOGIA;
        if (catGoogle.contains("drama") || catGoogle.contains("tragedy")) return Categoria.DRAMA;
        if (catGoogle.contains("history") || catGoogle.contains("historical")) return Categoria.HISTORIA;
        if (catGoogle.contains("science") || catGoogle.contains("nature")) return Categoria.CIENCIAS;
        if (catGoogle.contains("biography") || catGoogle.contains("autobiography")) return Categoria.BIOGRAFIA;
        if (catGoogle.contains("fiction")) return Categoria.FICCAO;

        return Categoria.OUTROS;
    }
}