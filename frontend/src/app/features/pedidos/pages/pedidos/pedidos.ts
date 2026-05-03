import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PedidoService } from '../../services/pedido.service';
import { Pedido } from '../../models/pedido.model';

@Component({
  selector: 'app-pedidos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './pedidos.html',
  styleUrl: './pedidos.css'
})
export class PedidosComponent implements OnInit {

  pedidos: Pedido[] = [];

  nuevo: Pedido = {
    descripcion: '',
    cantidad: 0,
    precio: 0,
    inventarioId: 0
  };

  editando = false;
  idEditando: number | null = null;
  modalVisible = false;
  mensajeModal = '';

  constructor(private pedidoService: PedidoService) {}

  ngOnInit(): void {
    this.cargarPedidos();
  }

  cargarPedidos(): void {
    this.pedidoService.getAll().subscribe(data => {
      this.pedidos = [...data];
    });
  }

  guardar(): void {
    this.mostrarModal(this.editando ? 'Actualizando pedido...' : 'Guardando pedido...');

    const operacion = this.editando && this.idEditando !== null
      ? this.pedidoService.update(this.idEditando, this.nuevo)
      : this.pedidoService.create(this.nuevo);

    operacion.subscribe({
      next: () => this.finalizarOperacion(),
      error: () => this.cerrarModal()
    });
  }

  editar(item: Pedido): void {
    this.nuevo = { ...item };
    this.editando = true;
    this.idEditando = item.id!;
  }

  eliminar(id: number): void {
    this.mostrarModal('Eliminando pedido...');
    this.pedidoService.delete(id).subscribe({
      next: () => this.finalizarOperacion(),
      error: () => this.cerrarModal()
    });
  }

  private finalizarOperacion(): void {
    this.cargarPedidos();
    this.resetFormulario();
    setTimeout(() => this.cerrarModal(), 800);
  }

  private resetFormulario(): void {
    this.nuevo = { descripcion: '', cantidad: 0, precio: 0, inventarioId: 0 };
    this.editando = false;
    this.idEditando = null;
  }

  private mostrarModal(mensaje: string): void {
    this.mensajeModal = mensaje;
    this.modalVisible = true;
  }

  private cerrarModal(): void {
    this.modalVisible = false;
  }
}
