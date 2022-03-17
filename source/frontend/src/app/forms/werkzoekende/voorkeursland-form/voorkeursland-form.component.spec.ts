import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VoorkeurslandFormComponent} from './voorkeursland-form.component';

describe('VoorkeurslandFormComponent', () => {
  let component: VoorkeurslandFormComponent;
  let fixture: ComponentFixture<VoorkeurslandFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VoorkeurslandFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VoorkeurslandFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
