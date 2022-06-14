import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { VehiculoDetails } from '../models/vehiculo-details-interface';
import { Vehiculo, VehiculoResponse } from '../models/vehiculo-interface';

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
export class VehiculoService {

  constructor(private http: HttpClient) { }

  getVehiculos(): Observable<VehiculoResponse> {
    return this.http.get<VehiculoResponse>(`${environment.apiBaseUrl}/vehiculo/`, DEFAULT_HEADERS);
  }

  getOneVehiculo(id:string): Observable<VehiculoDetails> {
    return this.http.get<VehiculoDetails>(`${environment.apiBaseUrl}/vehiculo/${id}`, DEFAULT_HEADERS);
  }

  editVehiculo(id:string, vehiculo: Vehiculo) {
    return this.http.put<VehiculoDetails>(`${environment.apiBaseUrl}/vehiculo/${id}`, DEFAULT_HEADERS)
  }

  createVehiculo(vehiculo: Vehiculo) {
    return this.http.post<Vehiculo>(`${environment.apiBaseUrl}/vehiculo`, vehiculo, DEFAULT_HEADERS);
  }

  deleteVehiculo(id: number): Observable<Vehiculo> {
    return this.http.delete<Vehiculo>(`${environment.apiBaseUrl}/vehiculo/${id}`, DEFAULT_HEADERS);
  }
}
