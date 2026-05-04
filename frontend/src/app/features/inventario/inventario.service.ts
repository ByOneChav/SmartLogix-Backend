// Servicio HTTP

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Inventario {
  id?: number;
  nombreProducto: string;
  ubicacion: string;
  stock: number;
  precio: number;
}

@Injectable({
  providedIn: 'root'
})
export class InventarioService {

  private apiUrl = 'http://localhost:8080/api/inventario';

  constructor(private http: HttpClient) {}

  // 📥 listar
  getAll(): Observable<Inventario[]> {
    return this.http.get<Inventario[]>(this.apiUrl);
  }

  // ➕ crear
  create(data: Inventario): Observable<Inventario> {
    return this.http.post<Inventario>(this.apiUrl, data);
  }

  // ✏️ actualizar
  update(id: number, data: Inventario) {
    return this.http.put(`${this.apiUrl}/${id}`, data);
  }

  // ❌ eliminar
  delete(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}