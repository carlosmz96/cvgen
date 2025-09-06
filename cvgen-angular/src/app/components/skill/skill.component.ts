import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Level } from '../../models/Level';

import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';
import { SelectModule } from 'primeng/select';

@Component({
  selector: 'app-skill',
  imports: [
    ReactiveFormsModule,
    InputTextModule,
    MessageModule,
    SelectModule,
    CommonModule
  ],
  templateUrl: './skill.component.html',
  styleUrl: './skill.component.scss'
})
export class SkillComponent {

  @Input() group!: FormGroup;

  levels: Level[] = [
    {
      label: 'Principiante',
      value: 'BEGINNER'
    },
    {
      label: 'Intermedio',
      value: 'INTERMEDIATE'
    },
    {
      label: 'Avanzado',
      value: 'ADVANCED'
    },
    {
      label: 'Experto',
      value: 'EXPERT'
    }
  ];

  isInvalid(field: string): boolean {
    const fieldToCheck = this.group.get(field);
    return fieldToCheck?.invalid! && fieldToCheck.touched;
  }

}
