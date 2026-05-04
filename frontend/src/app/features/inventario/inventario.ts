// Componente de Inventario (Standalone)

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InventarioService, Inventario } from './inventario.service';

@Component({
  selector: 'app-inventario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './inventario.html',
  styleUrls: ['./inventario.css']
})
export class InventarioComponent implements OnInit {

  // 📦 lista
  inventarios: Inventario[] = [];

  // 📝 formulario
  nuevo: Inventario = {
    nombreProducto: '',
    ubicacion: '',
    stock: null as any,
    precio: null as any
  };

  // ✏️ control edición
  editando: boolean = false;
  idEditando: number | null = null;

  // 🔄 estado modal (profesional)
  modalVisible: boolean = false;
  mensajeModal: string = '';

  constructor(private inventarioService: InventarioService) { }

  ngOnInit(): void {
    this.cargarInventario();
  }

  // 🔄 listar
  cargarInventario() {
    this.inventarioService.getAll().subscribe(data => {
      this.inventarios = [...data]; // fuerza refresco UI
    });
  }

  // 💾 guardar o actualizar
  guardar() {

    // 👉 mostrar modal
    this.mostrarModal(
      this.editando ? 'Actualizando inventario...' : 'Guardando inventario...'
    );

    if (this.editando && this.idEditando !== null) {

      // ✏️ UPDATE
      this.inventarioService.update(this.idEditando, this.nuevo)
        .subscribe({
          next: () => {
            this.finalizarOperacion();
          },
          error: () => this.cerrarModal()
        });

    } else {

      // 🆕 CREATE
      this.inventarioService.create(this.nuevo)
        .subscribe({
          next: () => {
            this.finalizarOperacion();
          },
          error: () => this.cerrarModal()
        });
    }
  }

  // ✏️ cargar datos al form
  editar(item: Inventario) {
    this.nuevo = { ...item };
    this.editando = true;
    this.idEditando = item.id!;
  }

  // ❌ eliminar
  eliminar(id: number) {

    this.mostrarModal('Eliminando inventario...');

    this.inventarioService.delete(id).subscribe({
      next: () => {
        this.finalizarOperacion();
      },
      error: () => this.cerrarModal()
    });
  }

  // 🔄 acciones comunes después de cualquier operación
  finalizarOperacion() {
    this.cargarInventario();  // refresca
    this.resetFormulario();   // limpia form
    setTimeout(() => this.cerrarModal(), 800); // pequeño delay visual
  }

  // 🧹 limpiar form
  resetFormulario() {
    this.nuevo = {
      nombreProducto: '',
      ubicacion: '',
      stock: 0,
      precio: 0
    };
    this.editando = false;
    this.idEditando = null;
  }

  // 🪟 modal helpers
  mostrarModal(mensaje: string) {
    this.mensajeModal = mensaje;
    this.modalVisible = true;
  }

  cerrarModal() {
    this.modalVisible = false;
  }
}