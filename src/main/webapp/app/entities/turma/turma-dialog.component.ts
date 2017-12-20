import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Turma } from './turma.model';
import { TurmaPopupService } from './turma-popup.service';
import { TurmaService } from './turma.service';

@Component({
    selector: 'jhi-turma-dialog',
    templateUrl: './turma-dialog.component.html'
})
export class TurmaDialogComponent implements OnInit {

    turma: Turma;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private turmaService: TurmaService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.turma.id !== undefined) {
            this.subscribeToSaveResponse(
                this.turmaService.update(this.turma));
        } else {
            this.subscribeToSaveResponse(
                this.turmaService.create(this.turma));
        }
    }

    private subscribeToSaveResponse(result: Observable<Turma>) {
        result.subscribe((res: Turma) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Turma) {
        this.eventManager.broadcast({ name: 'turmaListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-turma-popup',
    template: ''
})
export class TurmaPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private turmaPopupService: TurmaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.turmaPopupService
                    .open(TurmaDialogComponent as Component, params['id']);
            } else {
                this.turmaPopupService
                    .open(TurmaDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
