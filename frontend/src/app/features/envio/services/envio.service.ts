import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { Envio } from '../models/envio.model';

@Injectable({
  providedIn: 'root'
})
export class EnvioService {

  private apiUrl = `${environment.apiUrl}/api/envio`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Envio[]> {
    return this.http.get<Envio[]>(`${this.apiUrl}/all`);
  }

  create(data: Envio): Observable<Envio> {
    return this.http.post<Envio>(`${this.apiUrl}/create`, data);
  }

  update(id: number, data: Envio): Observable<Envio> {
    return this.http.put<Envio>(`${this.apiUrl}/update/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
