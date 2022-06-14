import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../components/login/login.component';
import { MarcaListComponent } from '../components/marca-list/marca-list.component';
import { TipoListComponent } from '../components/tipo-list/tipo-list.component';
import { UserListComponent } from '../components/user-list/user-list.component';
import { VehiculoListComponent } from '../components/vehiculo-list/vehiculo-list.component';

const routes: Routes = [
  {path: 'login', pathMatch: 'full', component: LoginComponent},
  {path: 'tipos', component: TipoListComponent},
  {path: 'marcas', component: MarcaListComponent},
  {path: 'vehiculos', component: VehiculoListComponent},
  {path: 'users', component: UserListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
