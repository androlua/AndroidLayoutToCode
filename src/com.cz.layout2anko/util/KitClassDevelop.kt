package com.cz.layout2anko.util

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.asJava.elements.KtLightElement
import org.jetbrains.kotlin.caches.resolve.KotlinCacheService
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.incremental.components.NoLookupLocation
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.resolve.lazy.ResolveSession
import java.util.*


/**
 * Created by cz on 2017/5/25.
 */
fun KtClass.findParams(): List<ValueParameterDescriptor> {
    val list = ArrayList<KtElement>()
    list.add(this)

    val resolveSession = KotlinCacheService.getInstance(project).getResolutionFacade(list).getFrontendService(ResolveSession::class.java)

    val classDescriptor = resolveSession.getClassDescriptor(this, NoLookupLocation.FROM_IDE)

    val valueParameters = ArrayList<ValueParameterDescriptor>()

    val constructorDescriptor = classDescriptor.unsubstitutedPrimaryConstructor

    if (constructorDescriptor != null) {
        val allParameters = constructorDescriptor.valueParameters
        for (parameter in allParameters) {
            valueParameters.add(parameter)
        }
    }
    return valueParameters
}

fun PsiElement.getKtClassForElement(): KtClass? {
    if (this is KtLightElement<*, *>) {
        val origin = originalElement
        if (origin != null) {
            return origin.getKtClassForElement()
        } else {
            return null
        }
    } else if (this is KtClass && !this.isEnum() &&
            !this.isInterface() &&
            !this.isAnnotation() &&
            !this.isSealed()) {
        return this
    } else {
        val parent = this?.parent
        if (null!=parent&&parent is KtElement) {
            return parent.getKtClassForElement()
        } else {
            return null
        }
    }
}
