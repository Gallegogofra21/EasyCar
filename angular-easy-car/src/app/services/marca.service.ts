import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { MarcaDetails } from '../models/marca-details-interface';
import { Marca, MarcaDto, MarcaResponse } from '../models/marca-interface';

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
export class MarcaService {

  constructor(private http: HttpClient) { }

  getMarcas(): Observable<MarcaResponse> {
    return this.http.get<MarcaResponse>(`${environment.apiBaseUrl}/marca/`, DEFAULT_HEADERS);
  }

  getOneMarca(id:string) {
    return this.http.get<MarcaDetails>(`${environment.apiBaseUrl}/marca/${id}`, DEFAULT_HEADERS);

  }

  editMarca(marca: MarcaDto, id:number, file: File) {
    let formData = new FormData();
    formData.append('marca', new Blob([JSON.stringify(marca)], {
      type:'application/json'
    }));
    formData.append("file", file);
    return this.http.put<Marca>(`${environment.apiBaseUrl}/marca/${id}`, formData, DEFAULT_HEADERS_TOKEN);
  }

  createMarca(marca: MarcaDto, file: File) {
    let formData = new FormData();
    formData.append('marca', new Blob([JSON.stringify(marca)], {
      type:'application/json'
    }));
    formData.append("file", file);
    return this.http.post<MarcaDto>(`${environment.apiBaseUrl}/marca/`, formData, DEFAULT_HEADERS_TOKEN);
  }

  deleteMarca(id: number): Observable<Marca> {
    return this.http.delete<Marca>(`${environment.apiBaseUrl}/marca/${id}`, DEFAULT_HEADERS);
  }
}
