import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Marca, MarcaDto } from 'src/app/models/marca-interface';
import { MarcaService } from 'src/app/services/marca.service';

@Component({
  selector: 'app-create-marca',
  templateUrl: './create-marca.component.html',
  styleUrls: ['./create-marca.component.css']
})
export class CreateMarcaComponent implements OnInit {

  file!: File;

  marcaDto = new MarcaDto();

  marcaResponse !: Marca;

  form = new FormGroup({
    nombre: new FormControl('')
  });

  constructor(private marcaService: MarcaService, private route: Router) { }

  ngOnInit(): void {
  }

  onFileChanged(event: any) {
    this.file = event.target.files[0];
  }

  doCreate() {
    this.marcaService.createMarca(this.marcaDto, this.file).subscribe(res => {
      this.route.navigate(['/marcas']);
    })
  }
}
