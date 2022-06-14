import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthLoginResponse } from '../models/auth-interface';
import { AuthLoginDto } from '../models/dto/AuthLoginDto';
import { RegisterResponse } from '../models/register-interface';

const AUTH_BASE_URL = 'auth';

const DEFAULT_HEADERS = {
  headers: new HttpHeaders ({
    'Content-Type' : 'application/json'
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

  registerAdmin(register: RegisterResponse): Observable<RegisterResponse> {
    let requestUrl = `${this.authBaseUrl}/register/admin`;
    return this.http.post<RegisterResponse>(requestUrl, register);
  }

  registerGestor(register: RegisterResponse): Observable<RegisterResponse> {
    let requestUrl = `${this.authBaseUrl}/register/gestor`;
    return this.http.post<RegisterResponse>(requestUrl, register)
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
