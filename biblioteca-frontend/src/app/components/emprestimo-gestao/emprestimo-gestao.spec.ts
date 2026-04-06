import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmprestimoGestaoComponent } from './emprestimo-gestao';

describe('EmprestimoGestao', () => {
  let component: EmprestimoGestaoComponent;
  let fixture: ComponentFixture<EmprestimoGestaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmprestimoGestaoComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(EmprestimoGestaoComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
