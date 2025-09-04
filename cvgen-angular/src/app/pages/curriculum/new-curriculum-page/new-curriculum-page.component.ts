import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';
import { SelectModule } from 'primeng/select';
import { TextareaModule } from 'primeng/textarea';

import { CountryService } from '../../../services/country/country.service';

@Component({
  selector: 'app-new-curriculum-page',
  imports: [ReactiveFormsModule, CommonModule, InputTextModule, MessageModule, SelectModule, TextareaModule],
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
      linkedinUrl: ['', [Validators.pattern(/^https?:\/\//), Validators.maxLength(512)]],
      githubUrl: ['', [Validators.pattern(/^https?:\/\//), Validators.maxLength(512)]],
      portfolioUrl: ['', [Validators.pattern(/^https?:\/\//), Validators.maxLength(512)]]
    });

    this.getCountries();
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

}
