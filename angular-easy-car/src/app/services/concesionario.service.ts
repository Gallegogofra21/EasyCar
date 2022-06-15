import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Concesionario, ConcesionarioDto, ConcesionarioResponse, CreateConcesionarioDto } from '../models/concesionario-interface';

const token = localStorage.getItem('request_token');

const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization' : `Bearer ${token}`
  })
};

const DEFAULT_HEADERS_TOKEN = {
  headers: new HttpHeaders({
    'Authorization' : `Bearer ${token}`
  })
};

@Injectable({
  providedIn: 'root'
})
export class ConcesionarioService {

  constructor(private http: HttpClient) { }

  getConcesionarios(): Observable<ConcesionarioResponse> {
    return this.http.get<ConcesionarioResponse>(`${environment.apiBaseUrl}/concesionario/`, DEFAULT_HEADERS);
  }

  getOneConcesionario(id: string) {
    return this.http.get<Concesionario>(`${environment.apiBaseUrl}/concesionario/${id}`, DEFAULT_HEADERS);
  }

  editConcesionario(concesionario: ConcesionarioDto, id: number) {
    let formData = new FormData();
    formData.append('concesionario', new Blob([JSON.stringify(concesionario)], {
      type:'application/json'
    }));
    return this.http.put<Concesionario>(`${environment.apiBaseUrl}/concesionario/${id}`, formData, DEFAULT_HEADERS_TOKEN)
  }

  createConcesionario(concesionario: CreateConcesionarioDto, id: any) {
    let formData = new FormData();
    formData.append('concesionario', new Blob([JSON.stringify(concesionario)], {
      type: 'application/json'
    }));
    return this.http.post<CreateConcesionarioDto>(`${environment.apiBaseUrl}/concesionario/${id}`, formData, DEFAULT_HEADERS_TOKEN);
  }

  deleteConcesionario(id: number): Observable<Concesionario> {
    return this.http.delete<Concesionario>(`${environment.apiBaseUrl}/concesionario/${id}`, DEFAULT_HEADERS);
  }
}
