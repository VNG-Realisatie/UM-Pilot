import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VoorkeurslandMatchComponent} from './voorkeursland-match.component';

describe('VoorkeurslandMatchComponent', () => {
  let component: VoorkeurslandMatchComponent;
  let fixture: ComponentFixture<VoorkeurslandMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VoorkeurslandMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VoorkeurslandMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
