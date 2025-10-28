import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { CommonModule } from '@angular/common';
import { AccordionModule } from 'primeng/accordion';
import { InputTextModule } from 'primeng/inputtext';
import { AuthService } from '../../../services/auth/auth.service';

@Component({
  selector: 'app-crear-curriculum',
  imports: [
    CommonModule,
    AccordionModule,
    ReactiveFormsModule,
    InputTextModule
  ],
  templateUrl: './crear-curriculum.component.html',
  styleUrl: './crear-curriculum.component.scss'
})
export class CrearCurriculumComponent {

  curriculumForm: FormGroup;

  name: string | undefined;

  constructor(
    private auth: AuthService,
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

}
