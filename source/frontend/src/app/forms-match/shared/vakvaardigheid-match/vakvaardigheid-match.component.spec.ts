import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VakvaardigheidMatchComponent} from './vakvaardigheid-match.component';

describe('VakvaardigheidMatchComponent', () => {
  let component: VakvaardigheidMatchComponent;
  let fixture: ComponentFixture<VakvaardigheidMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VakvaardigheidMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VakvaardigheidMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
