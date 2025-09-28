import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';

@Component({
  selector: 'app-register',
  imports: [
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    MessageModule,
    CommonModule,
    RouterModule
],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  registerForm: FormGroup;

  pswdNotEqual: boolean = false;
  pswdNotEqualMsg: string = 'El valor de la contraseña no coincide.';

  constructor(
    private fb: FormBuilder
  ) {
    this.registerForm = this.fb.group({
      fullname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      passwordRepeated: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    this.pswdNotEqual = false;
    if (this.registerForm.valid && this.registerForm.get('password')?.value == this.registerForm.get('passwordRepeated')?.value) {

    } else {
      this.registerForm.markAllAsTouched();
      this.pswdNotEqual = true;
      console.error(this.pswdNotEqualMsg);
    }
  }

  isInvalid(field: string): boolean {
    const fieldToCheck = this.registerForm.get(field);
    return fieldToCheck?.invalid! && fieldToCheck.touched;
  }

}
