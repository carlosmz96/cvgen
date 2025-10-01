import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { LoadingService } from './../../../services/loading/loading.service';

import { CountryService } from '../../../services/country/country.service';
import { CurriculumService } from '../../../services/curriculum/curriculum.service';

import { CertificationComponent } from '../../../components/certification/certification.component';
import { EducationComponent } from '../../../components/education/education.component';
import { ExperienceComponent } from '../../../components/experience/experience.component';
import { SkillComponent } from '../../../components/skill/skill.component';

import { Curriculum } from '../../../models/Curriculum';

import { AccordionModule } from 'primeng/accordion';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';
import { SelectModule } from 'primeng/select';
import { TextareaModule } from 'primeng/textarea';
import { JwtService } from '../../../services/jwt/jwt.service';

@Component({
  selector: 'app-curriculum-detail-page',
  imports: [
    ReactiveFormsModule,
    CommonModule,
    MessageModule,
    InputTextModule,
    SelectModule,
    TextareaModule,
    ButtonModule,
    AccordionModule,
    ExperienceComponent,
    SkillComponent,
    EducationComponent,
    CertificationComponent
  ],
  templateUrl: './curriculum-detail-page.component.html',
  styleUrl: './curriculum-detail-page.component.scss'
})
export class CurriculumDetailPageComponent {

  cvForm: FormGroup;

  countries: any[] = [];

  curriculumId: number;
  curriculum: Curriculum = {
    id: 0,
    fullName: '',
    title: '',
    email: '',
    locationCity: '',
    locationCountry: '',
    summary: '',
    linkedinUrl: '',
    githubUrl: '',
    portfolioUrl: '',
    createdAt: new Date(),
    updated: new Date(),
    experiences: [],
    skills: [],
    educations: [],
    certifications: [],
    userId: 0
  };

  constructor(
    private fb: FormBuilder,
    private countryService: CountryService,
    private cvService: CurriculumService,
    private loading: LoadingService,
    private jwtService: JwtService
  ) {
    this.cvForm = this.fb.group({
      fullName: ['', [Validators.required, Validators.maxLength(200)]],
      title: ['', [Validators.required, Validators.maxLength(120)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(320)]],
      locationCity: ['', [Validators.required, Validators.maxLength(120)]],
      locationCountry: ['', [Validators.required, Validators.maxLength(120)]],
      summary: ['', Validators.maxLength(2000)],
      experiences: this.fb.array([]),
      skills: this.fb.array([]),
      educations: this.fb.array([]),
      certifications: this.fb.array([]),
      linkedinUrl: ['', [Validators.pattern(/^https?:\/\//), Validators.maxLength(512)]],
      githubUrl: ['', [Validators.pattern(/^https?:\/\//), Validators.maxLength(512)]],
      portfolioUrl: ['', [Validators.pattern(/^https?:\/\//), Validators.maxLength(512)]]
    });

    this.curriculumId = Number(inject(ActivatedRoute).snapshot.paramMap.get('id'));
    this.getCountries();
  }

  ngOnInit(): void {
    this.loading.show('Cargando...');
    this.getCurriculum();
  }

  private getCountries(): void {
    this.countryService.getCountries().subscribe({
      next: (data: any[]) => {
        this.countries = data
          .map(e => ({
            label: e.translations.spa.common,
            value: e.translations.spa.common
          }))
          .sort((a, b) => a.label.localeCompare(b.label));
      }
    });
  }

  private getCurriculum(): void {
    this.cvService.getCurriculum(this.curriculumId).subscribe(
      {
        next: (data: Curriculum) => {
          this.curriculum = data;

          this.cvForm.patchValue(this.curriculum);

          // Experiencias
          this.experiences.clear();
          this.curriculum.experiences.forEach(exp => this.experiences.push(this.buildExperience(exp)));

          // Skills
          this.skills.clear();
          this.curriculum.skills.forEach(skill => this.skills.push(this.buildSkill(skill)));

          // Educaciones
          this.educations.clear();
          this.curriculum.educations.forEach(edu => this.educations.push(this.buildEducation(edu)));

          // Certificaciones
          this.certifications.clear();
          this.curriculum.certifications.forEach(cert => this.certifications.push(this.buildCertification(cert)));

          this.loading.hide();
        },
        error: err => {
          this.loading.hide();
          console.error('Error al obtener el currículum: ', err);
        }
      }
    );
  }

  onSubmit(): void {
    this.loading.show('Cargando...');
    this.curriculum = this.cvForm.getRawValue();
    this.curriculum.userId = this.jwtService.getUserId() as number;
    this.cvService.updateCurriculum(this.curriculum, this.curriculumId).subscribe(
      {
        next: (data: Curriculum) => {
          this.curriculum = data;
          this.loading.hide();
        },
        error: err => {
          this.loading.hide();
          console.error('Error al modificar los datos del CV: ', err);
        }
      }
    )
  }

  downloadCvPdf(): void {
    this.loading.show('Cargando...');
    const template: string = 'modern';
    this.cvService.downloadCurriculum(this.curriculumId, template).subscribe(
      {
        next: (data: Blob) => {
          const url = window.URL.createObjectURL(data);
          const a = document.createElement('a');
          a.href = url;
          a.download = 'cv-' + template + '.pdf';
          a.click();

          this.loading.hide();
        },
        error: err => {
          this.loading.hide();
          console.error('Error al descargar el CV: ', err);
        }
      }
    )
  }

  isInvalid(field: string): boolean {
    const fieldToCheck = this.cvForm.get(field);
    return fieldToCheck?.invalid! && fieldToCheck.touched;
  }

  // ======= Experiences =======

  get experiences(): FormArray<FormGroup> {
    return this.cvForm.get('experiences') as FormArray;
  }

  private buildExperience(exp?: any): FormGroup {
    return this.fb.group({
      position: [exp?.position || '', [Validators.required, Validators.maxLength(150)]],
      company: [exp?.company || '', [Validators.required, Validators.maxLength(150)]],
      location: [exp?.location || '', Validators.maxLength(150)],
      startDate: [exp?.startDate || null, Validators.required],
      endDate: [exp?.endDate || null],
      description: [exp?.description || '', Validators.maxLength(3000)],
      highlights: this.fb.array(
        (exp?.highlights || []).map((h: string) =>
          this.fb.control(h, Validators.maxLength(500))
        )
      )
    });
  }

  addExperience() {
    this.experiences.push(this.buildExperience());
  }

  removeExperience(index: number) {
    this.experiences.removeAt(index);
  }

  // ======= Skills =======
  get skills(): FormArray<FormGroup> {
    return this.cvForm.get('skills') as FormArray;
  }

  private buildSkill(skill?: any): FormGroup {
    return this.fb.group({
      name: [skill?.name || '', [Validators.required, Validators.maxLength(100)]],
      level: [skill?.level || '', Validators.maxLength(50)],
      category: [skill?.category || '', Validators.maxLength(50)]
    });
  }

  addSkill() {
    this.skills.push(this.buildSkill());
  }

  removeSkill(index: number) {
    this.skills.removeAt(index);
  }

  // ======= Educations =======
  get educations(): FormArray<FormGroup> {
    return this.cvForm.get('educations') as FormArray;
  }

  private buildEducation(edu?: any): FormGroup {
    return this.fb.group({
      degree: [edu?.degree || '', [Validators.required, Validators.maxLength(150)]],
      educationField: [edu?.educationField || '', [Validators.required, Validators.maxLength(150)]],
      institution: [edu?.institution || '', [Validators.required, Validators.maxLength(200)]],
      location: [edu?.location || '', Validators.maxLength(150)],
      startDate: [edu?.startDate || null, Validators.required],
      endDate: [edu?.endDate || null],
      description: [edu?.description || '', Validators.maxLength(2000)]
    });
  }

  addEducation() {
    this.educations.push(this.buildEducation());
  }

  removeEducation(index: number) {
    this.educations.removeAt(index);
  }

  // ======= Certifications =======
  get certifications(): FormArray<FormGroup> {
    return this.cvForm.get('certifications') as FormArray;
  }

  private buildCertification(cert?: any): FormGroup {
    return this.fb.group({
      name: [cert?.name || '', [Validators.required, Validators.maxLength(200)]],
      issuer: [cert?.issuer || '', [Validators.required, Validators.maxLength(200)]],
      dateObtained: [cert?.dateObtained || null, Validators.required],
      validUntil: [cert?.validUntil || null],
      credentialId: [cert?.credentialId || '', Validators.maxLength(120)],
      credentialUrl: [cert?.credentialUrl || '', Validators.maxLength(512)]
    });
  }

  addCertification() {
    this.certifications.push(this.buildCertification());
  }

  removeCertification(index: number) {
    this.certifications.removeAt(index);
  }

}
