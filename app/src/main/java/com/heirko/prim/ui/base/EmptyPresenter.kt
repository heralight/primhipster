package com.heirko.prim.ui.base

import javax.inject.Inject;

class EmptyPresenter @Inject constructor(): BasePresenter<EmptyView>() { }

interface EmptyView : PresenterView { }
