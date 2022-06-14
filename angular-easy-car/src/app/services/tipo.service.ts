import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TipoDetails } from '../models/tipo-details-interface';
import { Tipo, TipoDto, TipoResponse } from '../models/tipo-interface';

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
export class TipoService {

  constructor(private http: HttpClient) { }

  getTipos(): Observable<TipoResponse> {
    return this.http.get<TipoResponse>(`${environment.apiBaseUrl}/tipo/`, DEFAULT_HEADERS);
  }

  getOneTipo(id:String) {
    return this.http.get<TipoDetails>(`${environment.apiBaseUrl}/tipo/${id}`, DEFAULT_HEADERS);
  }

  editTipo(tipo: TipoDto, id:number, file: File) {
    let formData = new FormData();
    formData.append('tipo', new Blob([JSON.stringify(tipo)], {
      type:'application/json'
    }));
    formData.append("file", file);
    return this.http.put<Tipo>(`${environment.apiBaseUrl}/tipo/${id}`, formData, DEFAULT_HEADERS_TOKEN);
  }

  createTipo(tipo: Tipo) {
    return this.http.post<Tipo>(`${environment.apiBaseUrl}/tipo`, tipo, DEFAULT_HEADERS);
  }

  deleteTipo(id: number): Observable<Tipo> {
    return this.http.delete<Tipo>(`${environment.apiBaseUrl}/tipo/${id}`, DEFAULT_HEADERS);
  }
}
