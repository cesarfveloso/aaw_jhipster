import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AawAula3SharedModule } from '../../shared';
import {
    PermissaoService,
    PermissaoPopupService,
    PermissaoComponent,
    PermissaoDetailComponent,
    PermissaoDialogComponent,
    PermissaoPopupComponent,
    PermissaoDeletePopupComponent,
    PermissaoDeleteDialogComponent,
    permissaoRoute,
    permissaoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...permissaoRoute,
    ...permissaoPopupRoute,
];

@NgModule({
    imports: [
        AawAula3SharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PermissaoComponent,
        PermissaoDetailComponent,
        PermissaoDialogComponent,
        PermissaoDeleteDialogComponent,
        PermissaoPopupComponent,
        PermissaoDeletePopupComponent,
    ],
    entryComponents: [
        PermissaoComponent,
        PermissaoDialogComponent,
        PermissaoPopupComponent,
        PermissaoDeleteDialogComponent,
        PermissaoDeletePopupComponent,
    ],
    providers: [
        PermissaoService,
        PermissaoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AawAula3PermissaoModule {}
