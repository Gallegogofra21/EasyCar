export interface Vehiculo {
  id: number;
  version: string;
  modelo: string;
  fechaMatriculacion: string;
  kilometraje: string;
  potencia: string;
  marchas: string;
  precio: number;
  nombreMarca: string;
  tipo: number;
  llantas: string;
  distribucion: string;
  procedencia: string;
  traccion: string;
  foto1: string;
  foto2: string;
  foto3: string;
  foto4: string;
  concesionario: number;
}

export interface MarcaDetails {
  id: number;
  nombre: string;
  vehiculos: Vehiculo[];
  foto: string;
}
