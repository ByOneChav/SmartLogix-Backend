import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {
  name = '';
  email = '';
  password = '';
  rol = 'USER';
  error = '';
  loading = false;

  constructor(private authService: AuthService, private router: Router) {}

  register(): void {
    this.loading = true;
    this.error = '';

    this.authService.register({ name: this.name, email: this.email, password: this.password, rol: this.rol })
      .subscribe({
        next: () => {
          this.router.navigate(['/login']);
        },
        error: (err) => {
          this.error = err.error?.message || 'Error al registrar usuario';
          this.loading = false;
        }
      });
  }
}
