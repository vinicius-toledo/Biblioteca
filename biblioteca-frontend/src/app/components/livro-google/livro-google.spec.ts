import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LivroGoogleComponent } from './livro-google';

describe('LivroGoogle', () => {
  let component: LivroGoogleComponent;
  let fixture: ComponentFixture<LivroGoogleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LivroGoogleComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(LivroGoogleComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
