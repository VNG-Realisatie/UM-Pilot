import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WerkzoekendeListComponent } from './werkzoekende-list.component';

describe('WerkzoekendeListComponent', () => {
  let component: WerkzoekendeListComponent;
  let fixture: ComponentFixture<WerkzoekendeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WerkzoekendeListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerkzoekendeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
