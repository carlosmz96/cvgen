import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';
import { SelectModule } from 'primeng/select';
import { TextareaModule } from 'primeng/textarea';
import { ButtonModule } from 'primeng/button';

import { CountryService } from '../../../services/country/country.service';
import { ExperienceComponent } from "../../../components/experience/experience.component";
import { SkillComponent } from "../../../components/skill/skill.component";
import { EducationComponent } from "../../../components/education/education.component";
import { CertificationComponent } from "../../../components/certification/certification.component";

@Component({
  selector: 'app-new-curriculum-page',
  imports: [
    ReactiveFormsModule,
    CommonModule,
    InputTextModule,
    MessageModule,
    SelectModule,
    TextareaModule,
    ButtonModule,
    ExperienceComponent,
    SkillComponent,
    EducationComponent,
    CertificationComponent
],
  templateUrl: './new-curriculum-page.component.html',
  styleUrl: './new-curriculum-page.component.scss'
})
export class NewCurriculumPageComponent {

  cvForm: FormGroup;

  countries: string[] = [];

  constructor(
    private fb: FormBuilder,
    private countryService: CountryService
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

    this.getCountries();
  }

  ngOnInit(): void {

  }

  private getCountries(): void {
    this.countryService.getCountries().subscribe(
      {
        next: (data: any[]) => {
          data.forEach(e => {
            this.countries.push(e.translations.spa.common);
          });

          this.countries.sort((a: string, b: string) => a.localeCompare(b));
        }
      }
    );
  }

  onSubmit(): void {
    console.log(this.cvForm);
  }

  isInvalid(field: string): boolean {
    const fieldToCheck = this.cvForm.get(field);
    return fieldToCheck?.invalid! && fieldToCheck.touched;
  }

  // ======= Experiences =======

  get experiences(): FormArray<FormGroup> {
    return this.cvForm.get('experiences') as FormArray;
  }

  addExperience() {
    const expGroup = this.fb.group({
      position: ['', [Validators.required, Validators.maxLength(150)]],
      company: ['', [Validators.required, Validators.maxLength(150)]],
      location: ['', Validators.maxLength(150)],
      startDate: [null, Validators.required],
      endDate: [null],
      description: ['', Validators.maxLength(3000)],
      highlights: this.fb.array([])
    });
    this.experiences.push(expGroup);
  }

  removeExperience(index: number) {
    this.experiences.removeAt(index);
  }

  // ======= Skills =======
  get skills(): FormArray<FormGroup> {
    return this.cvForm.get('skills') as FormArray;
  }

  addSkill() {
    const skillGroup = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(100)]],
      level: ['', Validators.maxLength(50)],
      category: ['', Validators.maxLength(50)]
    });
    this.skills.push(skillGroup);
  }

  removeSkill(index: number) {
    this.skills.removeAt(index);
  }

  // ======= Educations =======
  get educations(): FormArray<FormGroup> {
    return this.cvForm.get('educations') as FormArray;
  }

  addEducation() {
    const educationGroup = this.fb.group({
      degree: ['', [Validators.required, Validators.maxLength(150)]],
      educationField: ['', [Validators.required, Validators.maxLength(150)]],
      institution: ['', [Validators.required, Validators.maxLength(200)]],
      location: ['', Validators.maxLength(150)],
      startDate: [null, Validators.required],
      endDate: [null],
      description: ['', Validators.maxLength(2000)],
    });
    this.educations.push(educationGroup);
  }

  removeEducation(index: number) {
    this.educations.removeAt(index);
  }

  // ======= Certifications =======
  get certifications(): FormArray<FormGroup> {
    return this.cvForm.get('certifications') as FormArray;
  }

  addCertification() {
    const certificationGroup = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(200)]],
      issuer: ['', [Validators.required, Validators.maxLength(200)]],
      dateObtained: [null, Validators.required],
      validUntil: [null],
      credentialId: ['', Validators.maxLength(120)],
      credentialUrl: ['', Validators.maxLength(512)]
    });
    this.certifications.push(certificationGroup);
  }

  removeCertification(index: number) {
    this.certifications.removeAt(index);
  }

}
