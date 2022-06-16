export interface Marca {
  id: any;
  nombre: string;
  foto: string;
  numVehiculos: number;
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

export interface MarcaResponse {
  content: Marca[];
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

export class MarcaDto {
  nombre: string;
  foto: string;


  constructor() {
    this.nombre = '';
    this.foto = '';
  }

}
