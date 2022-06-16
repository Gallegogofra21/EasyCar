import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthLoginResponse } from '../models/auth-interface';
import { AuthLoginDto } from '../models/dto/AuthLoginDto';
import { RegisterAdmin, RegisterGestor, RegisterGestorDto } from '../models/register-interface';

const AUTH_BASE_URL = 'auth';

const token = localStorage.getItem('request_token');

const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Authorization' : `Bearer ${token}`
  })
};


@Injectable({
  providedIn: 'root'
})

export class AuthService {
  authBaseUrl = `${environment.apiBaseUrl}/${AUTH_BASE_URL}`;

  constructor(private http: HttpClient) {}

  login(loginDto: AuthLoginDto): Observable<AuthLoginResponse> {
    let requestUrl = `${this.authBaseUrl}/login`;
    return this.http.post<AuthLoginResponse>(requestUrl, loginDto);
  }

  registerGestor(register: RegisterGestorDto, file: File) {
    let requestUrl = `${this.authBaseUrl}/register/gestor`;

    let formData = new FormData();
    formData.append('user', new Blob([JSON.stringify(register)], {
      type:'application/json'
    }));
    formData.append("file", file);
    return this.http.post<RegisterGestorDto>(requestUrl, formData, DEFAULT_HEADERS)
  }

  setLocalRequestToken(token: string) {
    localStorage.setItem('request_token', token);
  }

  setLocalUserName(name: string) {
    localStorage.setItem('name', name);
  }

  setLocalFoto(foto: string) {
    localStorage.setItem('foto', foto);
  }

  signOut() {
    localStorage.removeItem('request_token');
  }
}
