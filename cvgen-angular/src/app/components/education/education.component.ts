import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { DatePickerModule } from 'primeng/datepicker';
import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';
import { TextareaModule } from 'primeng/textarea';

@Component({
  selector: 'app-education',
  imports: [
    ReactiveFormsModule,
    InputTextModule,
    MessageModule,
    DatePickerModule,
    TextareaModule,
    CommonModule
  ],
  templateUrl: './education.component.html',
  styleUrl: './education.component.scss'
})
export class EducationComponent {

  @Input() group!: FormGroup;

  isInvalid(field: string): boolean {
    const fieldToCheck = this.group.get(field);
    return fieldToCheck?.invalid! && fieldToCheck.touched;
  }

}
