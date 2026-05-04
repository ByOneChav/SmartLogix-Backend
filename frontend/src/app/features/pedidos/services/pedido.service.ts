import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { Pedido } from '../models/pedido.model';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  private apiUrl = `${environment.apiUrl}/api/pedido`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Pedido[]> {
    return this.http.get<Pedido[]>(`${this.apiUrl}/all`);
  }

  create(data: Pedido): Observable<Pedido> {
    return this.http.post<Pedido>(`${this.apiUrl}/create`, data);
  }

  cambiarEstado(id: number, estado: string): Observable<Pedido> {
    return this.http.put<Pedido>(`${this.apiUrl}/update/${id}/estado?estado=${estado}`, {});
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
