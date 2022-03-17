import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VakvaardigheidFormComponent} from './vakvaardigheid-form.component';

describe('VakvaardigheidFormComponent', () => {
  let component: VakvaardigheidFormComponent;
  let fixture: ComponentFixture<VakvaardigheidFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VakvaardigheidFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VakvaardigheidFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
