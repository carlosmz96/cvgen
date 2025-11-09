import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { AccordionModule } from 'primeng/accordion';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { CheckboxModule } from 'primeng/checkbox';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { TextareaModule } from 'primeng/textarea';

import { Curriculum } from '../../../models/Curriculum';
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
    SelectModule,
    CalendarModule,
    CheckboxModule,
    DialogModule
  ],
  templateUrl: './crear-curriculum.component.html',
  styleUrl: './crear-curriculum.component.scss'
})
export class CrearCurriculumComponent {

  curriculumForm: FormGroup;

  themes = [
    { label: 'Por defecto', value: 'template' },
    { label: 'Tema Oscuro', value: 'oscuro' },
    { label: 'Tema Azul', value: 'azul' }
  ];
  languages = [
    { label: 'Español', value: 'es' },
    { label: 'Inglés', value: 'en' }
  ];

  showPreview = false;

  constructor(
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

      experiences: this.fb.array([]),
      educations: this.fb.array([]),
      skills: this.fb.array([]),
      certifications: this.fb.array([]),
      languageSkills: this.fb.array([])
    });
  }

  ngOnInit(): void {}

  guardarCurriculum(): void {
    if (this.curriculumForm.valid) {
      const formValue = this.curriculumForm.value;
      const curriculumData: Curriculum = {
        ...formValue,
        user: {
          name: formValue.name,
          email: formValue.email
        }
      }

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
    if (this.curriculumForm.valid) {
      const formValue = this.curriculumForm.value;
      const curriculumData: Curriculum = {
        ...formValue,
        user: {
          name: formValue.name,
          email: formValue.email
        }
      }

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

  // atajo para el FormArray (en la clase del componente)
  get experiencesArray(): FormArray {
    return this.curriculumForm.get('experiences') as FormArray;
  }

  // acceder al FormGroup de una experiencia por índice
  fgExp(i: number): FormGroup {
    return this.experiencesArray.at(i) as FormGroup;
  }

  // crear un FormGroup de experiencia
  private createExperienceGroup(): FormGroup {
    return this.fb.group({
      id: [null],
      position: ['', Validators.required],
      company: ['', Validators.required],
      location: ['', Validators.required],
      startDate: [null, Validators.required],
      endDate: [null],
      current: [false],
      description: ['']
    });
  }

  // handlers de añadir / eliminar
  addExperience(): void {
    this.experiencesArray.push(this.createExperienceGroup());
  }

  removeExperience(index: number): void {
    this.experiencesArray.removeAt(index);
  }

}
