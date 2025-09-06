import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { DatePickerModule } from 'primeng/datepicker';
import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';

@Component({
  selector: 'app-certification',
  imports: [
    ReactiveFormsModule,
    InputTextModule,
    MessageModule,
    DatePickerModule,
    CommonModule
  ],
  templateUrl: './certification.component.html',
  styleUrl: './certification.component.scss'
})
export class CertificationComponent {

  @Input() group!: FormGroup;

  isInvalid(field: string): boolean {
    const fieldToCheck = this.group.get(field);
    return fieldToCheck?.invalid! && fieldToCheck.touched;
  }

}
