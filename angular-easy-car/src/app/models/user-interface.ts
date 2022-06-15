export interface User {
  id: any;
  username: string;
  nombre: string;
  apellidos: string;
  telefono: string;
  email: string;
  avatar: string;
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

export interface UserResponse {
  content: User[];
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

export class UserDto {
  username: string;
  nombre: string;
  apellidos: string;
  telefono: string;
  email: string;
  avatar: string;

  constructor() {
    this.username = '';
    this.nombre = '';
    this.apellidos = '';
    this.telefono = '';
    this.email = '';
    this.avatar = '';
  }
}
