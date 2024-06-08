import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  cpf: string = '';
  senha: string = '';
  errorMessage: string | null = null;

  constructor(private authService: AuthService) { }

  onSubmit(): void {
    this.authService.fazerLogin(this.cpf, this.senha).subscribe(
      response => {
        console.log('Login bem-sucedido:', response);
        // Redirecionar ou tratar o sucesso do login
      },
      error => {
        console.error('Erro de login:', error);
        if (error.status === 400) {
          this.errorMessage = 'CPF e senha são obrigatórios';
        } else if (error.status === 401) {
          this.errorMessage = 'CPF ou senha inválidos';
        } else {
          this.errorMessage = 'Erro ao processar login. Tente novamente mais tarde.';
        }
      }
    );
  }
}
