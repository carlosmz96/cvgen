import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormArray, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';
import { DatePickerModule } from 'primeng/datepicker';
import { TextareaModule } from 'primeng/textarea';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-experience',
  imports: [
    ReactiveFormsModule,
    InputTextModule,
    MessageModule,
    DatePickerModule,
    TextareaModule,
    ButtonModule,
    CommonModule
  ],
  templateUrl: './experience.component.html',
  styleUrl: './experience.component.scss'
})
export class ExperienceComponent {

  @Input() group!: FormGroup;

  isInvalid(field: string): boolean {
    const fieldToCheck = this.group.get(field);
    return fieldToCheck?.invalid! && fieldToCheck.touched;
  }

  get highlights(): FormArray {
    return this.group.get('highlights') as FormArray;
  }

  addHighlight() {
    this.highlights.push(new FormControl('', [Validators.maxLength(500)]));
  }

  removeHighlight(index: number) {
    this.highlights.removeAt(index);
  }

}
