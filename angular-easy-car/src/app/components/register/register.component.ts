import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterAdmin, RegisterAdminDto, RegisterGestor, RegisterGestorDto } from 'src/app/models/register-interface'
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerDto = new RegisterGestorDto();

  registerResponse !: RegisterGestor;
  form = new FormGroup({
    username: new FormControl(''),
    nombre: new FormControl(''),
    apellidos: new FormControl(''),
    telefono: new FormControl(''),
    email: new FormControl(''),
    fechaNacimiento: new FormControl(''),
    password: new FormControl(''),
    password2: new FormControl(''),
    rol: new FormControl('')
  })

  constructor(private authService: AuthService, private route: Router) { }

  ngOnInit(): void {
  }

  doRegister() {
    this.authService.registerGestor(this.registerResponse).subscribe(res => {
      window.open(`${environment.apiBaseUrl}/usuarios/`)
    })
    }

}
