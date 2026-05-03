import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InventarioService } from '../../services/inventario.service';
import { Inventario } from '../../models/inventario.model';

@Component({
  selector: 'app-inventario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './inventario.html',
  styleUrl: './inventario.css'
})
export class InventarioComponent implements OnInit {

  inventarios: Inventario[] = [];

  nuevo: Inventario = {
    nombreProducto: '',
    ubicacion: '',
    stock: 0,
    precio: 0
  };

  editando = false;
  idEditando: number | null = null;
  modalVisible = false;
  mensajeModal = '';

  constructor(private inventarioService: InventarioService) {}

  ngOnInit(): void {
    this.cargarInventario();
  }

  cargarInventario(): void {
    this.inventarioService.getAll().subscribe(data => {
      this.inventarios = [...data];
    });
  }

  guardar(): void {
    this.mostrarModal(this.editando ? 'Actualizando producto...' : 'Guardando producto...');

    const operacion = this.editando && this.idEditando !== null
      ? this.inventarioService.update(this.idEditando, this.nuevo)
      : this.inventarioService.create(this.nuevo);

    operacion.subscribe({
      next: () => this.finalizarOperacion(),
      error: () => this.cerrarModal()
    });
  }

  editar(item: Inventario): void {
    this.nuevo = { ...item };
    this.editando = true;
    this.idEditando = item.id!;
  }

  eliminar(id: number): void {
    this.mostrarModal('Eliminando producto...');
    this.inventarioService.delete(id).subscribe({
      next: () => this.finalizarOperacion(),
      error: () => this.cerrarModal()
    });
  }

  private finalizarOperacion(): void {
    this.cargarInventario();
    this.resetFormulario();
    setTimeout(() => this.cerrarModal(), 800);
  }

  private resetFormulario(): void {
    this.nuevo = { nombreProducto: '', ubicacion: '', stock: 0, precio: 0 };
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
