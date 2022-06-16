import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Tipo, TipoDto } from 'src/app/models/tipo-interface';
import { TipoService } from 'src/app/services/tipo.service';

@Component({
  selector: 'app-create-tipo',
  templateUrl: './create-tipo.component.html',
  styleUrls: ['./create-tipo.component.css']
})
export class CreateTipoComponent implements OnInit {

  file!: File;

  tipoDto = new TipoDto();

  tipoResponse !: Tipo;

  form = new FormGroup({
    nombre: new FormControl(''),
  });

  constructor(private tipoService: TipoService, private route: Router) { }

  ngOnInit(): void {
  }

  onFileChanged(event: any) {
    this.file = event.target.files[0];
  }

  doCreate() {
    this.tipoService.createTipo(this.tipoDto, this.file).subscribe(res => {
      this.route.navigate(['/tipos']);
    })
  }

}
