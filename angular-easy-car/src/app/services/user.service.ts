import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User, UserResponse } from '../models/user-interface';

const token = localStorage.getItem('request_token');

const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization' : `Bearer ${token}`
  })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUsers(): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${environment.apiBaseUrl}/usuario/`, DEFAULT_HEADERS);
  }

  createGestor(): Observable<User> {
    return this.http.post<User>(`${environment.apiBaseUrl}/auth/register/gestor`, DEFAULT_HEADERS);
  }

  deleteUser(id: number): Observable<User> {
    return this.http.delete<User>(`${environment.apiBaseUrl}/usuario/${id}`, DEFAULT_HEADERS);
  }
}
