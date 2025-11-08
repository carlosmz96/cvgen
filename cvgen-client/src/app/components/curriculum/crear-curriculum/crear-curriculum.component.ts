import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { AccordionModule } from 'primeng/accordion';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { TextareaModule } from 'primeng/textarea';
import { DropdownModule } from 'primeng/dropdown';

import { AuthService } from '../../../services/auth/auth.service';
import { CurriculumService } from '../../../services/curriculum/curriculum.service';

@Component({
  selector: 'app-crear-curriculum',
  imports: [
    CommonModule,
    AccordionModule,
    ReactiveFormsModule,
    InputTextModule,
    ButtonModule,
    TextareaModule,
    DropdownModule
  ],
  templateUrl: './crear-curriculum.component.html',
  styleUrl: './crear-curriculum.component.scss'
})
export class CrearCurriculumComponent {

  curriculumForm: FormGroup;

  name: string | undefined;

  themes = [
    { label: 'Por defecto', value: 'template' },
    { label: 'Tema Oscuro', value: 'oscuro' },
    { label: 'Tema Azul', value: 'azul' }
  ];
  languages = [
    { label: 'Español', value: 'es' },
    { label: 'Inglés', value: 'en' }
  ];

  constructor(
    private auth: AuthService,
    private curriculumService: CurriculumService,
    private fb: FormBuilder
  ) {
    this.curriculumForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      country: ['', Validators.required],
      city: ['', Validators.required],
      phone: [''],
      title: ['', Validators.required],
      summary: [''],
      theme: ['', Validators.required],
      language: ['', Validators.required],
      experiences: [[]],
      educations: [[]],
      skills: [[]],
      certifications: [[]],
      languageSkills: [[]]
    });
  }

  ngOnInit(): void {
    this.name = this.auth.user()?.name;
  }

  guardarCurriculum(): void {
    if (this.curriculumForm.valid) {
      const curriculumData = this.curriculumForm.value;
      console.log('Guardando el currículum:', curriculumData);

      this.curriculumService.crearCurriculum(curriculumData).subscribe({
        next: (response) => {
          console.log('Currículum guardado con éxito:', response);
        },
        error: (error) => {
          console.error('Error al guardar el currículum:', error);
        }
      });
    } else {
      this.curriculumForm.markAllAsTouched();
      console.log('El formulario no es válido');
    }
  }

  descargarCurriculum(): void {
    const debug = Object.entries(this.curriculumForm.controls).map(([k, c]: any) => ({
      control: k,
      value: c.value,
      valid: c.valid,
      errors: c.errors,
      status: c.status
    }));
    console.table(debug);
    if (this.curriculumForm.valid) {
      const curriculumData = this.curriculumForm.value;
      this.curriculumService.generarPdf(curriculumData).subscribe({
        next: (blob) => {
          const url = window.URL.createObjectURL(blob);
          const a = document.createElement('a');
          a.href = url;
          a.download = 'curriculum.pdf';
          a.click();
          window.URL.revokeObjectURL(url);
        },
        error: (error) => {
          console.error('Error al generar el PDF del currículum:', error);
        }
      });
    } else {
      this.curriculumForm.markAllAsTouched();
      console.log('El formulario no es válido');
    }
  }

}
