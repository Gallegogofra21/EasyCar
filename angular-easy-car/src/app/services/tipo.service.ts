import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TipoDetails } from '../models/tipo-details-interface';
import { Tipo, TipoResponse } from '../models/tipo-interface';

const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class TipoService {

  constructor(private http: HttpClient) { }

  getTipos(): Observable<TipoResponse> {
    return this.http.get<TipoResponse>(`${environment.apiBaseUrl}/tipo/`);
  }

  getOneTipo(id:String) {
    return this.http.get<TipoDetails>(`${environment.apiBaseUrl}/tipo/${id}`);
  }

  editTipo(id:string, tipo: Tipo) {
    return this.http.put<TipoDetails>(`${environment.apiBaseUrl}/tipo/${id}`, tipo, DEFAULT_HEADERS);
  }

  createTipo(tipo: Tipo) {
    return this.http.post<Tipo>(`${environment.apiBaseUrl}/tipo`, tipo, DEFAULT_HEADERS);
  }

  deleteTipo(id: number): Observable<TipoDetails> {
    return this.http.delete<TipoDetails>(`${environment.apiBaseUrl}/tipo/${id}`);
  }
}
