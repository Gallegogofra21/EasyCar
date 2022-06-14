export interface Concesionario {
  id: any;
  nombre: string;
  direccion: string;
  numVehiculos: number;
  usuarioId: number;
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

export interface ConcesionarioResponse {
  content: Concesionario[];
  pageable: Pageable;
  last: boolean;
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
  sort: Sort2;
  first: boolean;
  numberOfElements: number;
  empty: boolean;
}

export class ConcesionarioDto {
  nombre: string;
  direccion: string;


  constructor() {
    this.nombre = '';
    this.direccion = '';
  }
}
