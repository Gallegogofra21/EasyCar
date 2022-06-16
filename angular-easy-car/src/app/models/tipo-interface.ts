export interface Tipo {
  id: any;
  nombre: string;
  foto: string;
  numVehiculos: any;
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

export interface TipoResponse {
  content: Tipo[];
  pageable: Pageable;
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  number: number;
  sort: Sort2;
  numberOfElements: number;
  first: boolean;
  empty: boolean;
}

export class TipoDto {
  nombre: string;
  foto: string;


  constructor() {
    this.nombre = '';
    this.foto = '';
  }
}
