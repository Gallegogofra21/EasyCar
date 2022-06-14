import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { MarcaDetails } from '../models/marca-details-interface';
import { Marca, MarcaResponse } from '../models/marca-interface';

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
export class MarcaService {

  constructor(private http: HttpClient) { }

  getMarcas(): Observable<MarcaResponse> {
    return this.http.get<MarcaResponse>(`${environment.apiBaseUrl}/marca/`, DEFAULT_HEADERS);
  }

  getOneMarca(id:string) {
    return this.http.get<MarcaDetails>(`${environment.apiBaseUrl}/marca/${id}`, DEFAULT_HEADERS);

  }

  editMarca(id:string, marca: Marca) {
    return this.http.put<MarcaDetails>(`${environment.apiBaseUrl}/marca/${id}`, DEFAULT_HEADERS);
  }

  createMarca(marca: Marca) {
    return this.http.post<Marca>(`${environment.apiBaseUrl}/marca`, marca, DEFAULT_HEADERS);
  }

  deleteMarca(id: number): Observable<Marca> {
    return this.http.delete<Marca>(`${environment.apiBaseUrl}/marca/${id}`, DEFAULT_HEADERS);
  }
}
