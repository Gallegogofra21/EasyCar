import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Tipo, TipoResponse } from "../models/tipo-interface";
import { AuthService } from "./auth.service";

const tipoUrl = `${environment.apiBaseUrl}/tipo`
const token = localStorage.getItem('request_token');

const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Content-Type' : 'application/json',
    'Authorization' : `Bearer ${token}`
   })
};

@Injectable({
  providedIn: 'root'
})

export class TipoService {
  constructor(private http: HttpClient, private authService: AuthService) { }

  getTipos(limit: number): Observable<TipoResponse> {
    return this.http.get<TipoResponse>(tipoUrl, DEFAULT_HEADERS);
  }


}
