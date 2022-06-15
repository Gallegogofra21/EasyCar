export interface Vehiculo {
  id: any;
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

export interface Sort {
  empty: boolean;
  sorted: boolean;
  unsorted: boolean;
}

export interface Pageable {
  sort: Sort;
  offset: number;
  pageNumber: number;
  pageSize: number;
  paged: boolean;
  unpaged: boolean;
}

export interface Sort2 {
  empty: boolean;
  sorted: boolean;
  unsorted: boolean;
}

export interface VehiculoResponse {
  content: Vehiculo[];
  pageable: Pageable;
  last: boolean;
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  sort: Sort2;
  first: boolean;
  numberOfElements: number;
  empty: boolean;
}

export class VehiculoDto {
  version: string;
  modelo: string;
  fechaMatriculacion: string;
  kilometraje: string;
  potencia: string;
  marchas: string;
  precio: number;
  llantas: string;
  distribucion: string;
  procedencia: string;
  traccion: string;
  foto1: string;
  foto2: string;
  foto3: string;
  foto4: string;


  constructor() {
    this.version = '';
    this.modelo = '';
    this.fechaMatriculacion = '';
    this.kilometraje = '';
    this.potencia = '';
    this.marchas = '';
    this.precio = 0;
    this.llantas = '';
    this.distribucion = '';
    this.procedencia = '';
    this.traccion = '';
    this.foto1 = '';
    this.foto2 = '';
    this.foto3 = '';
    this.foto4 = '';
  }
}
