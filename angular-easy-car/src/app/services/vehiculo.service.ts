import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { VehiculoDetails } from '../models/vehiculo-details-interface';
import { CreateVehiculoDto, Vehiculo, VehiculoDto, VehiculoResponse } from '../models/vehiculo-interface';

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
export class VehiculoService {

  constructor(private http: HttpClient) { }

  getVehiculos(): Observable<VehiculoResponse> {
    return this.http.get<VehiculoResponse>(`${environment.apiBaseUrl}/vehiculo/`, DEFAULT_HEADERS);
  }

  getOneVehiculo(id:string): Observable<VehiculoDetails> {
    return this.http.get<VehiculoDetails>(`${environment.apiBaseUrl}/vehiculo/${id}`, DEFAULT_HEADERS);
  }

  editVehiculo(vehiculo: VehiculoDto, id: number, file1: File) {
    let formData = new FormData();
    formData.append('vehiculo', new Blob([JSON.stringify(vehiculo)], {
      type:'application/json'
    }));
    formData.append("file1", file1);
    // formData.append("file2", file2);
    // formData.append("file3", file3);
    // formData.append("file4", file4);
    return this.http.put<Vehiculo>(`${environment.apiBaseUrl}/vehiculo/${id}`, formData, DEFAULT_HEADERS_TOKEN);
  }

  createVehiculo(vehiculo: CreateVehiculoDto, file: File, id: any) {
    let formData = new FormData();
    formData.append('vehiculo', new Blob([JSON.stringify(vehiculo)], {
      type: 'application/json'
    }));
    formData.append('file', file);
    return this.http.post<Vehiculo>(`${environment.apiBaseUrl}/vehiculo/${id}`, formData, DEFAULT_HEADERS_TOKEN);
  }

  deleteVehiculo(id: number): Observable<Vehiculo> {
    return this.http.delete<Vehiculo>(`${environment.apiBaseUrl}/vehiculo/${id}`, DEFAULT_HEADERS);
  }
}
