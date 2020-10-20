/*
 *  This file is part of the Jikes RVM project (http://jikesrvm.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License. You
 *  may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  See the COPYRIGHT.txt file distributed with this work for information
 *  regarding copyright ownership.
 */
package org.jikesrvm.mm.mminterface;

import org.vmmagic.pragma.*;
import org.vmmagic.unboxed.*;

@Uninterruptible
public class SSContext extends MMTkMutatorContext {
    // DEFAULT: BumpAllocator #0 (CopySpace)
    // CODE/READONLY: BumpAllocator #1 (ImmortalSpace)
    // LOS: LargeObjectAllocator #0 (LargeObjectSpace)

    @Inline
    protected final int getAllocatorTag(int allocator) {
        if (allocator == MMTkAllocator.LOS) {
            return MMTkMutatorContext.TAG_LARGE_OBJECT;
        } else {
            return MMTkMutatorContext.TAG_BUMP_POINTER;
        }
    }
    @Inline
    protected final int getAllocatorIndex(int allocator) {
        if (allocator == MMTkAllocator.DEFAULT) {
            return 0;
        } else if (allocator == MMTkAllocator.IMMORTAL || allocator == MMTkAllocator.CODE || allocator == MMTkAllocator.READONLY) {
            return 1;
        } else {
            return 0;
        }
    }
    @Inline
    protected final int getSpaceTag(int allocator) {
        if (allocator == MMTkAllocator.DEFAULT) {
            return COPY_SPACE;
        } else if (allocator == MMTkAllocator.LOS) {
            return LARGE_OBJECT_SPACE;
        } else {
            return IMMORTAL_SPACE;
        }
    }
}
