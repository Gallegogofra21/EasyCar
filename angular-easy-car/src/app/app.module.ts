import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './modules/app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { SideMenuComponent } from './shared/side-menu/side-menu.component';
import { MaterialImportsModule } from './modules/material-imports.module';
import { TipoListComponent } from './components/tipo-list/tipo-list.component';
import { TipoItemComponent } from './components/tipo-item/tipo-item.component';
import { MarcaListComponent } from './components/marca-list/marca-list.component';
import { MarcaItemComponent } from './components/marca-item/marca-item.component';
import { VehiculoListComponent } from './components/vehiculo-list/vehiculo-list.component';
import { VehiculoItemComponent } from './components/vehiculo-item/vehiculo-item.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UserItemComponent } from './components/user-item/user-item.component';
import { RegisterComponent } from './components/register/register.component';
import { DialogEditTipoComponent } from './components/dialog-edit-tipo/dialog-edit-tipo.component';
import { RxReactiveFormsModule } from '@rxweb/reactive-form-validators';
import { DialogEditMarcaComponent } from './components/dialog-edit-marca/dialog-edit-marca.component';
import { ConcesionarioListComponent } from './components/concesionario-list/concesionario-list.component';
import { ConcesionarioItemComponent } from './components/concesionario-item/concesionario-item.component';
import { DialogEditConcesionarioComponent } from './components/dialog-edit-concesionario/dialog-edit-concesionario.component';
import { DialogEditVehiculoComponent } from './components/dialog-edit-vehiculo/dialog-edit-vehiculo.component';
import { DialogEditUserComponent } from './components/dialog-edit-user/dialog-edit-user.component';
import { CreateTipoComponent } from './components/create-tipo/create-tipo.component';
import { CreateMarcaComponent } from './components/create-marca/create-marca.component';
import { CreateConcesionarioComponent } from './components/create-concesionario/create-concesionario.component';
import { CreateVehiculoComponent } from './components/create-vehiculo/create-vehiculo.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SideMenuComponent,
    TipoListComponent,
    TipoItemComponent,
    MarcaListComponent,
    MarcaItemComponent,
    VehiculoListComponent,
    VehiculoItemComponent,
    UserListComponent,
    UserItemComponent,
    RegisterComponent,
    DialogEditTipoComponent,
    DialogEditMarcaComponent,
    ConcesionarioListComponent,
    ConcesionarioItemComponent,
    DialogEditConcesionarioComponent,
    DialogEditVehiculoComponent,
    DialogEditUserComponent,
    CreateTipoComponent,
    CreateMarcaComponent,
    CreateConcesionarioComponent,
    CreateVehiculoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialImportsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    FlexLayoutModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    RxReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
