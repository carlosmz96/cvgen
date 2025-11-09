import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { AccordionModule } from 'primeng/accordion';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { TextareaModule } from 'primeng/textarea';
import { DialogModule } from 'primeng/dialog';
import { ProgressSpinnerModule } from 'primeng/progressspinner';

import { Curriculum } from '../../../models/Curriculum';
import { CurriculumService } from '../../../services/curriculum/curriculum.service';

@Component({
  selector: 'app-consultar-curriculum',
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
    DialogModule,
    ProgressSpinnerModule
  ],
  templateUrl: './detalle-curriculum.component.html',
  styleUrl: './detalle-curriculum.component.scss'
})
export class DetalleCurriculumComponent {

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

  curriculumId: string;
  cargando: boolean = false;

  constructor(
    private curriculumService: CurriculumService,
    private fb: FormBuilder,
    private route: ActivatedRoute
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

    this.cargando = true;

    this.curriculumId = this.route.snapshot.paramMap.get('id')!;
  }

  ngOnInit(): void {
    this.obtenerCurriculum();
  }

  private obtenerCurriculum(): void {
    this.curriculumService.obtenerPorId(Number(this.curriculumId)).subscribe(
      {
        next: (cv: Curriculum) => {
          this.patchCurriculum(cv);

          // modo solo lectura
          this.curriculumForm.disable({ emitEvent: false });
          this.cargando = false;
        },
        error: err => {
          this.cargando = false;
          console.error('Error al obtener el currículum', err);
        }
      }
    );
  }

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

  // ============== HELPERS ==============

  get experiencesArray(): FormArray {
    return this.curriculumForm.get('experiences') as FormArray;
  }
  get educationsArray(): FormArray {
    return this.curriculumForm.get('educations') as FormArray;
  }
  get skillsArray(): FormArray {
    return this.curriculumForm.get('skills') as FormArray;
  }
  get certsArray(): FormArray {
    return this.curriculumForm.get('certifications') as FormArray;
  }
  get langSkillsArray(): FormArray {
    return this.curriculumForm.get('languageSkills') as FormArray;
  }

  // acceder al FormGroup de una experiencia por índice
  fgExp(i: number): FormGroup {
    return this.experiencesArray.at(i) as FormGroup;
  }

  // acceder al FormGroup de una educación por índice
  fgEdu(i: number): FormGroup {
    return this.educationsArray.at(i) as FormGroup;
  }

  // acceder al FormGroup de una habilidad por índice
  fgSkl(i: number): FormGroup {
    return this.skillsArray.at(i) as FormGroup;
  }

  // acceder al FormGroup de un certificado por índice
  fgCer(i: number): FormGroup {
    return this.certsArray.at(i) as FormGroup;
  }

  // acceder al FormGroup de una habilidad de lenguaje por índice
  fgLan(i: number): FormGroup {
    return this.langSkillsArray.at(i) as FormGroup;
  }

  // ============== CRUD EXPERIENCIA ==============

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

  // ============== CRUD EDUCACIÓN ==============

  private createEducationGroup(): FormGroup {
    return this.fb.group({
      id: [null],
      degree: [''],
      institution: [''],
      fieldOfStudy: [''],
      startDate: [null],
      endDate: [null],
      description: ['']
    });
  }

  // handlers de añadir / eliminar
  addEducation(): void {
    this.educationsArray.push(this.createEducationGroup());
  }

  removeEducation(index: number): void {
    this.educationsArray.removeAt(index);
  }

  // ============== CRUD HABILIDAD ==============

  private createSkillGroup(): FormGroup {
    return this.fb.group({
      id: [null],
      name: [''],
      level: [''],
      category: ['']
    });
  }

  // handlers de añadir / eliminar
  addSkill(): void {
    this.skillsArray.push(this.createSkillGroup());
  }

  removeSkill(index: number): void {
    this.skillsArray.removeAt(index);
  }

  // ============== CRUD CERTIFICACIÓN ==============

  private createCertGroup(): FormGroup {
    return this.fb.group({
      id: [null],
      name: [''],
      issuer: [''],
      obtainedDate: [null],
      validUntil: [null],
      credentialUrl: ['']
    });
  }

  // handlers de añadir / eliminar
  addCertification(): void {
    this.certsArray.push(this.createCertGroup());
  }

  removeCertification(index: number): void {
    this.certsArray.removeAt(index);
  }

  // ============== CRUD HABILIDAD DE LENGUAJE ==============

  private createLangSkillGroup(): FormGroup {
    return this.fb.group({
      id: [null],
      language: [''],
      level: ['']
    });
  }

  // handlers de añadir / eliminar
  addLangSkill(): void {
    this.langSkillsArray.push(this.createLangSkillGroup());
  }

  removeLangSkill(index: number): void {
    this.langSkillsArray.removeAt(index);
  }

  // ============== FUNCIONES AUXILIARES ==============

  private patchCurriculum(cv: Curriculum) {
    // Campos simples
    this.curriculumForm.patchValue({
      name: cv.user?.name ?? '',
      email: cv.user?.email ?? '',
      country: cv.country ?? '',
      city: cv.city ?? '',
      phone: cv.phone ?? '',
      title: cv.title ?? '',
      summary: cv.summary ?? '',
      theme: cv.theme ?? 'template',
      language: cv.language ?? 'es'
    });

    // Arrays
    this.setArray(this.experiencesArray, cv.experiences, () => this.createExperienceGroup(), (fg, e) => fg.patchValue(e));
    this.setArray(this.educationsArray, cv.educations, () => this.createEducationGroup(), (fg, e) => fg.patchValue(e));
    this.setArray(this.skillsArray, cv.skills, () => this.createSkillGroup(), (fg, s) => fg.patchValue(s));
    this.setArray(this.certsArray, cv.certifications, () => this.createCertGroup(), (fg, c) => fg.patchValue(c));
    this.setArray(this.langSkillsArray, cv.languageSkills, () => this.createLangSkillGroup(), (fg, l) => fg.patchValue(l));
  }

  private setArray<T>(
    formArray: FormArray,
    items: T[] | undefined,
    factory: () => FormGroup,
    patcher: (fg: FormGroup, item: any) => void
  ) {
    formArray.clear();
    (items ?? []).forEach(item => {
      const fg = factory();
      patcher(fg, item);
      formArray.push(fg);
    });
  }

}
