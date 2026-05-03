import { Component, OnInit } from '@angular/core';
import { DashboardCard } from '../../components/dashboard-card/dashboard-card';
import { DashboardTable } from '../../components/dashboard-table/dashboard-table';
import { PedidoService } from '../../../pedidos/services/pedido.service';
import { InventarioService } from '../../../inventario/services/inventario.service';
import { EnvioService } from '../../../envio/services/envio.service';
import { Pedido } from '../../../pedidos/models/pedido.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [DashboardCard, DashboardTable],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {

  columnas = ['ID', 'Descripción', 'Cantidad', 'Precio', 'Inventario ID'];
  pedidos: Pedido[] = [];

  totalInventario = '—';
  totalPedidos = '—';
  totalEnvios = '—';

  constructor(
    private pedidoService: PedidoService,
    private inventarioService: InventarioService,
    private envioService: EnvioService
  ) {}

  ngOnInit(): void {
    this.pedidoService.getAll().subscribe({
      next: data => {
        this.pedidos = data;
        this.totalPedidos = String(data.length);
      }
    });

    this.inventarioService.getAll().subscribe({
      next: data => this.totalInventario = String(data.length)
    });

    this.envioService.getAll().subscribe({
      next: data => this.totalEnvios = String(data.length)
    });
  }
}
