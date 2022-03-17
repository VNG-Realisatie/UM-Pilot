import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RemoveItemSvgComponent} from './remove-item-svg.component';

describe('RemoveItemSvgComponent', () => {
  let component: RemoveItemSvgComponent;
  let fixture: ComponentFixture<RemoveItemSvgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RemoveItemSvgComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RemoveItemSvgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
